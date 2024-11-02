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
                                 sh   'mvn test';
                             }
                            }
              stage('Mockito integration'){
              steps {
                  echo 'Test unitaire integration';
                       sh   'mvn verify -DskipTests';
                                                         }
                                                        }


         stage('Maven build'){
                      steps {
                          echo 'Maven build';
                               sh   'mvn clean install';
                                                                 }
                                                                }
         stage('sonar'){
         steps{
         withSonarQubeEnv(credentialsId: 'sonar-api-key') {
             sh'mvn clean package sonar:sonar';
         }
         }}
    }
}