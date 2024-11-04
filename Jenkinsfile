pipeline {
    agent any
    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'NourHassine_5ds4_groupe3',
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
                script {
                    withSonarQubeEnv(credentialsId: 'sonar-api') {
                        sh 'mvn clean package sonar:sonar'
                    }
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
                nexusUrl: '192.168.50.4:8081',
                nexusVersion: 'nexus3',
                protocol: 'http',
                repository: 'projetspring',
                version: "1.0"
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t tpachatproject:v1.${BUILD_ID} ."
                sh "docker tag tpachatproject:v1.${BUILD_ID} nour174/tpachatproject:v1.${BUILD_ID}"
                sh "docker tag tpachatproject:v1.${BUILD_ID} nour174/tpachatproject:latest"
            }
        }
        /*
        stage('Push to Docker Hub') {
            steps {
                withCredentials([string(credentialsId: 'git_creds', variable: 'docker_hub_cred')]) {
                    sh 'docker login -u khouloudzograni -p ${docker_hub_cred}'
                    sh 'docker push khouloudzograni/tpachatproject:v1.${BUILD_ID}'
                    sh 'docker push khouloudzograni/tpachatproject:latest'
                }
            }
        }

        stage('Deploy using Docker Compose') {
            steps {
                sh 'docker-compose down || true'
                sh 'docker rm -f mysql || true'
                sh 'docker-compose up -d'
            }
        }
    }

    post {
        success {
            // Notify on successful build
            mail to: 'khouloud.zograni@esprit.tn',
                 subject: "Pipeline Success: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "The build was successful. Check it out here: ${env.BUILD_URL}"
        }
        failure {
            // Notify on failed build
            mail to: 'khouloud.zograni@esprit.tn',
                 subject: "Pipeline Failure: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "The build has failed. Check it out here: ${env.BUILD_URL}"
        }*/
    }
}
