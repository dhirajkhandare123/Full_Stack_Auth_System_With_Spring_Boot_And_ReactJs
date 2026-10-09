//package com.substring.security;
//
//import com.substring.helper.UserHelper;
//import com.substring.repository.UserRepository;
//import io.jsonwebtoken.*;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.GregorianCalendar;
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//@Component
//@RequiredArgsConstructor
//public class JWTAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JWTService jwtService;
//    private final UserRepository userRepository;
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String header = request.getHeader("Authorization");
//        if(header!=null && header.startsWith("Bearer")){
//            // token extract and validate krna then authentication create and then security then content ke andar set krna
//            String token = header.substring(7);
//
//            try{
//
//                Jws<Claims> parse = jwtService.parse(token);
//                Claims payload = parse.getPayload();
//                String userId = payload.getSubject();
//                UUID userUuid = UserHelper.parseUUID(userId);
//                userRepository.findById(userUuid)
//                        .ifPresent(user -> {
//                            List<GrantedAuthority> authorities =
//                                    user
//                                            .getRoles()==null ? List.of(): user.getRoles()
//                                                                                                    .stream()
//                                                                                                    .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//                        });
//
//            } catch (ExpiredJwtException e){
//                e.printStackTrace();
//
//            } catch (MalformedJwtException e){
//                e.printStackTrace();
//            } catch (JwtException e){
//                e.printStackTrace();
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//
//        filterChain.doFilter(request,response);
//    }
//}
