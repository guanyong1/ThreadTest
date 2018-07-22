import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionModifyExceptionTest {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("张三",28));
        users.add(new User("李四",25));
        users.add(new User("王五",31));
        Iterator iterator = users.iterator();
        while (iterator.hasNext()){
            User user = (User) iterator.next();
            User u = (new User("王五",31));
            System.out.println(u.equals(user));
            if(u.equals(user)){
                users.remove(user);
            }else {
                System.out.println(user);
            }
        }
    }
}
