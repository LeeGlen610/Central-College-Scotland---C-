# Central-College-Scotland---C-
A replication of an assessment I did in forth valley for Object-oriented Programming using C++. As this program has generally 
of what it is in OOP it is a good way to understand how to develop programs in OOP using C++. In reality it's basically me just messing around with what I can do using OOP with C++ and why you'd use it. 

What I've come to realise is that Java is a much easier experience developing in a Object-oriented methodology as it is designed to do so whilst C++ I have to go through loop holes just to get what I want so take for example toString:

# toString - Java Vs C++

Java -

In Java generally when creating a class you'd override Object's toString to make a toString specific for that class so for example:

public class Person {

private String name;

private int age;

private char gender;

public Person(){

  name = "";
  
  age = 0;
  
  gender = 'N'; // N - Null
  
}// End Constructor

public Person(String name, int age, char gender){
    
    this.name = name;
    
    this.age = age;
    
    this.gender = gender;

} //End Constructor

String toString(){

    return name + ", " + age + ", " + gender + "\n";
    
}// This toString returns the name, age and gender of the person class as a string.

}

Whilst in C++ you can't return the age as it is a number and not a string cause C++ just wont do the conversion. What you'll need to do is overload the operators particulry << so for example:

Person.h - Creation of the header file -

#define PERSON_H

#ifndef PERSON_H

#include <iostream>
  
using namespace std;

class Person{

private:

string name;

int age;

char gender;

public:

void Person(void);

void Person(string name, int age, char gender);

string toString(void); 

friend inline ostream operator<<(const operator& op, const Person& person); 

/*Up above with the operator<< and toString is the two different ways i've implemented a toString - I like the toString more as you'll see why*/.

}

#endif

- End Person.h

Person.cpp - Implementing the functions - 

#include "Person.h"

void Person::Person(){

  name = "";
  
  age = 0;
  
  gender = 'N';
  
}

void Person::Person(string name, int age, char gender){

  this->name = name;
  
  this->age = age;
  
  this->gender = gender;
  
}

string Person::toString(){

    ostringstream buffer;
    
    buffer << name << ", " << age << ", " << gender << endl;
    
    return buffer.str();
    
} //I prefer this as it is in more the line of how you'd see it in Java.

friend inline ostream operator<<(const operator& op, const Person& person){

    return op << person.name << ", " << person.age << ", " << person.gender << endl;
    
} /*Friend means that it'll allow me access to the private attributes as it is regarded as a friendly function and is defined out of the classes scope.*/

- End Person.cpp

# TO-DO List
<li>
  <ul><b><del>Class Declaration</del></b></ul>
  <ul><b><del>Inheritance</del></b></ul>
  <ul><b><del>Virtual Methods</del></b></ul>
  <ul><b><del>Equivalent of this. in C++</del></b></ul>
  <ul><b><del>Instance of equivelent.</del></b></ul>
  <ul><b>GUI Creation for C++ - May go to another programming language instead like python or C# when
    I eventually get to this part.</b></ul>
  <ul><b>Serialization.</b></ul>
  <ul><b><del>Overriding Operators - <<, >>, == etc</ul></b></del>
  </li>

