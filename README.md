# DevOps-Assessment

* build Project

``mvn clean install``

This project is Containerize using Jib plugin (Jib is a Maven plugin for building Docker and OCI images for your Java applications.)

[Jib Github Repo](https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin) 


###For local enviroments
1. Build image with jib plugin
   
   ``mvn compile jib:dockerBuild``
 
2. Execute compose file.
    
    ``docker-compose up -d``