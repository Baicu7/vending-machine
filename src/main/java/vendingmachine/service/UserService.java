package vendingmachine.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vendingmachine.model.User;
import vendingmachine.util.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getValue()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    private User findByUsername(String username) {
        User seller = new User();
        seller.setUsername("seller");
        seller.setPassword("seller");
        seller.setRole(Role.SELLER);

        User buyer = new User();
        buyer.setUsername("buyer");
        buyer.setPassword("buyer");
        buyer.setRole(Role.BUYER);

        List<User> users = new ArrayList<>();
        users.add(seller);
        users.add(buyer);
        return users.stream().filter(u -> u.getUsername().equalsIgnoreCase(username)).collect(Collectors.toList()).get(0);
    }
}
