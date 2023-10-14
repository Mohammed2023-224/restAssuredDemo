package model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//created pojo class for the users
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"fname",
"lname",
"age",
"id",
"address"
})

public class Person {

@JsonProperty("fname")
private String fname;
@JsonProperty("lname")
private String lname;
@JsonProperty("age")
private Integer age;
@JsonProperty("id")
private Integer id;
@JsonProperty("address")
private String address;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("fname")
public String getFname() {
return fname;
}

@JsonProperty("fname")
public void setFname(String fname) {
this.fname = fname;
}

@JsonProperty("lname")
public String getLname() {
return lname;
}

@JsonProperty("lname")
public void setLname(String lname) {
this.lname = lname;
}

@JsonProperty("age")
public Integer getAge() {
return age;
}

@JsonProperty("age")
public void setAge(Integer age) {
this.age = age;
}

@JsonProperty("id")
public Integer getId() {
return id;
}

@JsonProperty("id")
public void setId(Integer id) {
this.id = id;
}

@JsonProperty("address")
public String getAddress() {
return address;
}

@JsonProperty("address")
public void setAddress(String address) {
this.address = address;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}