package sg.edu.nus.iss.app.visaWorkshop14.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.app.visaWorkshop14.model.Contact;

@Repository
public interface RedisRepo {
    public void save(final Contact ctc);
    public Contact findById(final String contactId);
    public List<Contact> findAll(int startIndex);
}
