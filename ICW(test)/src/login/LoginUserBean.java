package login;

import java.io.Serializable;

public class LoginUserBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String id_;
    private String name_;
    private String pass_id_;
    public String getPass_id_() {
		return pass_id_;
	}

	public void setPass_id_(String pass_id_) {
		this.pass_id_ = pass_id_;
	}

	public String getUser_pass_() {
		return user_pass_;
	}

	public void setUser_pass_(String user_pass_) {
		this.user_pass_ = user_pass_;
	}

	private String user_pass_;
    
    

    public LoginUserBean() {
        this.id_ = "";
        this.name_ = "";
    }
    
    public LoginUserBean(String id, String name, String pass, String pass_id) {
        this.id_ = id;
        this.name_ = name;
        this.user_pass_ = pass;
        this.pass_id_ = pass_id;
        
    }
    
    public void setId(String id){
        this.id_ = id;
    }
    
    public String getId(){
        return this.id_;
    }
    
    public void setName(String name){
        this.name_ = name;
    }
    
    public String getName(){
        return this.name_;
    }
    
    

}
