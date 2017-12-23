import com.firebase.security.token.TokenGenerator;
import com.firebase.security.token.TokenOptions;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestToken {

    @Test
    public void Test() throws InterruptedException {
        Map<String, Object> authPayload = new HashMap<String, Object>();
        authPayload.put("uid", "1");
        authPayload.put("some", "arbitrary");
        authPayload.put("data", "here");

        TokenOptions tokenOptions = new TokenOptions();
        tokenOptions.setAdmin(true);
        tokenOptions.setDebug(true);

        TokenGenerator tokenGenerator = new TokenGenerator("<YOUR_FIREBASE_SECRET>");
        String token = tokenGenerator.createToken(authPayload);

        System.out.println(tokenGenerator.createToken(authPayload));
        Thread.currentThread().sleep(1000L);
        System.out.println(tokenGenerator.createToken(authPayload));
        Thread.currentThread().sleep(1000L);
        System.out.println(tokenGenerator.createToken(authPayload));

    }
}
