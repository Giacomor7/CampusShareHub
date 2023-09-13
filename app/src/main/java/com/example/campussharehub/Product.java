package com.example.campussharehub;

import java.math.BigDecimal;

public class Product {
    public String Name;
    public BigDecimal Price;
    public byte[] Image;
    public String Description;
    public String CollectionInformation;

    // Constructor parameters should perhaps be optional and have defaults
    // To do this would require a separate builder class (this is why C# is better than java)
    // For our current purposes, we assume that we will always pass in all the parameters...
    public Product(String name, BigDecimal price, byte[] image, String description, String collectionInformation){
        Name = name;
        Price = price;
        Image = image;
        Description = description;
        CollectionInformation = collectionInformation;
    }
}
