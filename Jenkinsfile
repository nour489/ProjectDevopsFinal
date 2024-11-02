pipeline {
    agent any
    stages{
              stage('GIT'){
               steps {

                       echo 'Pulling... ';
                      git branch: 'KhouloudZograni_5ds_groupe3',
                          url: 'https://github.com/nour489/ProjectDevopsFinal.git'
               }
              }

              stage('Maven Clean'){
               steps {
                   echo 'Clean du projet: ';
                   sh   'mvn clean'
               }
              }
              stage('Mockito'){
                             steps {
                                 echo 'Test unitaire ';
                                 sh   'mvn test'
                             }
                            }
    }
}