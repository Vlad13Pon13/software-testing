package manager;

import models.ContactData;
import models.GroupData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class JdbcHelper  extends HelperBase{


    public JdbcHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

   public ArrayList<GroupData> getGroupListJdbc(){
        var groups = new ArrayList<GroupData>();
       try(var connection = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
           var statement = connection.createStatement();
            var result = statement.executeQuery("select group_id, group_name, group_header, group_footer from group_list"))
       {
           while (result.next()){
               groups.add(new
                       GroupData().withHId(result.getString("group_id"))
                       .withName(result.getString("group_name"))
                       .withHeader(result.getString("group_header"))
                       .withFooter(result.getString("group_footer")));
           }

       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       
       return groups;
   }

    public Long getGroupCountJdbc() throws SQLException {
        try(var connection = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
            var statement = connection.createStatement();
            var result = statement.executeQuery("select count(group_id) as count from group_list"))
        {
            if (result.next()) {
                return result.getLong("count");
            } else {
                return 0L;
            }
        }

    }

   public ArrayList<ContactData> getContactListJdbc() throws SQLException {
        var contacts = new ArrayList<ContactData>();
        try(var connection = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
            var statement = connection.createStatement();
            var result = statement.executeQuery("select id, firstname, lastname, address, mobile, email from addressbook"))
        {
            while (result.next()){

                ContactData contact = new ContactData();
                contact.setId(result.getString("id"));
                contact.setFirstName(result.getString("firstname"));
                contact.setLastName(result.getString("lastname"));
                contact.setAddress(result.getString("address"));
                contact.setMobile(result.getString("mobile"));
                contact.setMail(result.getString("email"));

                contacts.add(contact);

            }

        }
        return contacts;
   }

   public Long getContactCountJdbc() throws SQLException {
       try(var connection = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
           var statement = connection.createStatement();
           var result = statement.executeQuery("select count(id) as count from addressbook"))
       {
           if (result.next()) {
               return result.getLong("count");
           } else {
               return 0L;
           }
       }

   }



}
