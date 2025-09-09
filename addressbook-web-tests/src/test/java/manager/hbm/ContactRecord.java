package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    @Column(name = "id")
    public int id;

    @Column(name = "firstname")
    public String firstname;

    @Column(name = "lastname")
    public String lastname;

    @Column(name = "address")
    public String address;

    @Column(name = "mobile")
    public String mobile;

    @Column(name = "email")
    public String email;

    @Column(name = "middlename")
    public String middlename="";

    @Column(name = "nickname")
    public String nickname="";

    @Column(name = "company")
    public String company="";

    @Column(name = "title")
    public String title="";

    @Column(name = "home")
    public String home="";

    @Column(name = "work")
    public String work="";

    @Column(name = "fax")
    public String fax="";

    @Column(name = "email2")
    public String email2="";

    @Column(name = "email3")
    public String email3="";

    @Column(name = "homepage")
    public String homepage="";








    public ContactRecord(){

    }

    public ContactRecord(int id, String firstname, String lastname, String address, String mobile, String email){

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.mobile = mobile;
        this.email = email;

    }


}
