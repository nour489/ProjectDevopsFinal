pipeline {
    agent any
    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'KhouloudZograni_5ds_groupe3',
                    url: 'https://github.com/nour489/ProjectDevopsFinal.git'
            }
        }

        stage('Maven Build') {
            steps {
                sh 'mvn clean package install'
            }
        }

        stage('Unit Tests Mockito') {
            steps {
                sh 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv(credentialsId: 'sonar-api-key')
                       {
                         sh'mvn clean package sonar:sonar';
                       }
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'target/tpAchatProject-1.0.jar', fingerprint: true
            }
        }

        stage('Publish to Nexus') {
            steps {
                nexusArtifactUploader artifacts: [
                    [artifactId: 'tpAchatProject', classifier: '', file: 'target/tpAchatProject-1.0.jar', type: 'jar']
                ],
                credentialsId: 'nexus-auth',
                groupId: 'com.esprit.examen',
                nexusUrl: 'http://192.168.50.4:8081',
                nexusVersion: 'nexus3',
                protocol: 'http',
                repository: 'projet-spring',
                version: "1.0"
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t tpachatproject:v1.${BUILD_ID} ."
                sh "docker tag tpachatproject:v1.${BUILD_ID} khouloudzograni/tpachatproject:v1.${BUILD_ID}"
                sh "docker tag tpachatproject:v1.${BUILD_ID} khouloudzograni/tpachatproject:latest"
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([string(credentialsId: 'docker_hub_cred', variable: 'docker_hub_cred')]) {
                    sh 'docker login -u khouloudzograni -p ${docker_hub_cred}'
                    sh 'docker push khouloudzograni/tpachatproject:v1.${BUILD_ID}'
                    sh 'docker push khouloudzograni/tpachatproject:latest'
                }
            }
        }

        stage('Deploy using Docker Compose') {
            steps {
                sh 'docker-compose down'
                sh 'docker-compose up -d'
            }
        }
    }
}
