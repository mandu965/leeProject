package lee.test.spring.aop.service;
 
import org.springframework.stereotype.Service;
 
@Service("customerService") 
public class CustomerServiceImpl implements CustomerService {
 
    public void addCustomer() {
         System.out.println("[CustomerServiceImpl] addCustomer");
     } 

    public void getCustomer() {
         System.out.println("[CustomerServiceImpl] getCustomer");
     } 

    public void deleteCustomer() {
         System.out.println("[CustomerServiceImpl] deleteCustomer");
     } 

    public void getCustomerList() {
         System.out.println("[CustomerServiceImpl] getCustomerList");
     } 

    public void updateCustomer() {
         System.out.println("[CustomerServiceImpl] updateCustomer");
     } 

} 
