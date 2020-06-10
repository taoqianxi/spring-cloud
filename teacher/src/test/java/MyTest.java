import com.teacher.TeacherApplication;
import com.teacher.util.JedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TeacherApplication.class)
public class MyTest {
    @Autowired
    private JedisUtil jedisUtil;
    @Test
    public void te(){
        jedisUtil.get();
    }
}
