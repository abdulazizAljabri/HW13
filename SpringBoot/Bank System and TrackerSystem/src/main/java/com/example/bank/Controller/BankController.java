package com.example.bank.Controller;

import com.example.bank.ApiResponse.ApiResponse;
import com.example.bank.Model.Customers;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/bank/")
public class BankController {

    ArrayList<Customers> customerList = new ArrayList<>();

    @GetMapping("customers")
    public ArrayList<Customers> getCustomer() {
        return customerList;
    }

    @PostMapping("add")
    public ApiResponse addCustomer(@RequestBody Customers customers) {
        customerList.add(customers);
        return new ApiResponse("done .",200);
    }

    @PutMapping("update/{index}")
    public ApiResponse updateCustomer(@PathVariable int index, @RequestBody Customers customers) {
        customerList.set(index, customers);
        return new ApiResponse("Updat is done.",200);
    }

    @DeleteMapping("delete/{index}")
    public ApiResponse deleteCustomer(@PathVariable int index) {
        customerList.remove(index);
        return new ApiResponse("Done.",200);
    }
    @PutMapping("deposit/{id}/{money}")
    public ApiResponse depositCustomer(@PathVariable String  id , @PathVariable int money) {
        for (Customers customer : customerList)
        {
            if (customer.getId().equals(id)){
                int newBalance = customer.getBalance() + money;
                customer.setBalance(newBalance);
                return new ApiResponse("Success deposit : ",200);
            }
        }
        return new ApiResponse("there is no Customer with this id  : ",200);
    }

    @PutMapping("withdraw/{id}/{money}")
    public ApiResponse withdrawCustomer(@PathVariable String id , @PathVariable int money) {
        for (Customers customer : customerList)
        {
            if (customer.getId().equals(id))
            {
                if(customer.getBalance() > money)
                {
                    int newBalance = customer.getBalance()-money;
                    customer.setBalance(newBalance);
                    return new ApiResponse("Success withdraw : ",200);
                }
            }
        }

        return new ApiResponse("sorry you don't have enough : ",200);
    }
}
