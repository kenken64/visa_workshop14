package sg.edu.nus.iss.app.visaWorkshop14.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.app.visaWorkshop14.model.Contact;

@Service
public class ContactsRepo implements RedisRepo{
    private Logger logger = Logger.getLogger(ContactsRepo.class.getName());
    private static final String CONTACT_ENTITY = "contactlist";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    
    @Override
    public void save(final Contact ctc){
        redisTemplate.opsForList().leftPush(CONTACT_ENTITY, ctc.getId());
        redisTemplate.opsForHash().put(CONTACT_ENTITY + "_Map", ctc.getId(), ctc);
    }

    @Override
    public Contact findById(final String contactId){
        Contact result = (Contact)redisTemplate.opsForHash()
            .get(CONTACT_ENTITY + "_Map", contactId);
        return result;
    }

    @Override
    public List<Contact> findAll(int startIndex){
        List<Object> fromContactList = redisTemplate.opsForList()
            .range(CONTACT_ENTITY, startIndex, startIndex + 9);
        List<Contact> ctcs = 
            (List<Contact>)redisTemplate.opsForHash()
                .multiGet(CONTACT_ENTITY + "_Map", fromContactList)
                .stream()
                .filter(Contact.class::isInstance)
                .map(Contact.class::cast)
                .toList();

        return ctcs;
    }

}
