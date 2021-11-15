import io.jsonwebtoken.Claims;

public class Test {
    public static void main(String[] args) {
        JwtUtil util = new JwtUtil();
        String generateToken = util.generateToken("Ranjeet weds Larika", "marraige", "143");
        System.out.println(generateToken);
        Claims claims = util.getClaims("143", generateToken);
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getExpiration());
        System.out.println("======================");
        String username = util.getUsername("143", generateToken);
        System.out.println(username);
        System.out.println(util.getExpDate(generateToken));
        System.out.println(util.isTokenExp(generateToken));
        System.out.println(util.validateToken("marraige",generateToken));

    }
}
