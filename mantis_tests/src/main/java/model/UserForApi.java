package model;

public record UserForApi(String userName,
                         String password,
                         String realName,
                         String email,
                         Long accessLevel,
                         boolean enabled,
                         boolean protect) {

    public UserForApi(){
        this("","password","","",25L,true, false);
    }

    public UserForApi withName(String userName){
        return new UserForApi(userName, this.password, this.realName, this.email, this.accessLevel, this.enabled,this.protect);

    }

    public UserForApi withPassword(String password){
        return new UserForApi(this.userName, password, this.realName, this.email, this.accessLevel, this.enabled,this.protect);

    }

    public UserForApi withRealName(String realName){
        return new UserForApi(this.userName, this.password, realName, this.email, this.accessLevel, this.enabled,this.protect);

    }

    public UserForApi withEmail(String email){
        return new UserForApi(this.userName, this.password, this.realName, email, this.accessLevel, this.enabled,this.protect);

    }



}
