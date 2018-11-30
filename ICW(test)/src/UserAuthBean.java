
public class UserAuthBean {

  /** Creates a new instance of UserAuthBean */
  public UserAuthBean() {
  }

  public boolean execute(User us) {
    if (us.getName().equals("") == true && us.getPassword().equals("") == true) {
      return true;
    } else {
      return false;
    }
  }
}