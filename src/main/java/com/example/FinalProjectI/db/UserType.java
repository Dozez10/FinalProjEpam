package com.example.FinalProjectI.db;
// type can be client, master , administrator
/**
 *
 * @author Ivan Manuilenko
 */
public enum UserType
{
      CLIENT("CLIENT"),MASTER("MASTER"),ADMINISTRATOR("ADMINISTRATOR");
      private final String type;
      private UserType(String type)
      {
             this.type = type;
      }

}
