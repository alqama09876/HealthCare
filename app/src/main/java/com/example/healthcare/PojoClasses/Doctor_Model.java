package com.example.healthcare.PojoClasses;

public class Doctor_Model {
    String id, Name, Specification, Affiliated_Hospital_Name, Office_Hours;

    public Doctor_Model(String name, String specification, String affiliated_Hospital_Name, String office_Hours) {
        Name = name;
        Specification = specification;
        Affiliated_Hospital_Name = affiliated_Hospital_Name;
        Office_Hours = office_Hours;
    }

    public Doctor_Model() {
    }

    public Doctor_Model(String id, String name, String specification, String affiliated_Hospital_Name, String office_Hours) {
        this.id = id;
        Name = name;
        Specification = specification;
        Affiliated_Hospital_Name = affiliated_Hospital_Name;
        Office_Hours = office_Hours;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSpecification() {
        return Specification;
    }

    public void setSpecification(String specification) {
        Specification = specification;
    }

    public String getAffiliated_Hospital_Name() {
        return Affiliated_Hospital_Name;
    }

    public void setAffiliated_Hospital_Name(String affiliated_Hospital_Name) {
        Affiliated_Hospital_Name = affiliated_Hospital_Name;
    }

    public String getOffice_Hours() {
        return Office_Hours;
    }

    public void setOffice_Hours(String office_Hours) {
        Office_Hours = office_Hours;
    }
}
