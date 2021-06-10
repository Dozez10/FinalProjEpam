package com.example.FinalProjectI.db;
// type can be client, master , administrator
public enum UserType
{
      CLIENT("CLIENT"),MASTER("MASTER"),ADMINISTRATOR("ADMINISTRATOR");
      private final String type;
      private UserType(String type)
      {
             this.type = type;
      }

}
