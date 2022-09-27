pipeline {
    agent any
    tools {
        gradle 'gradle'
    }
    stages{
        stage('Build JAR File'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/MotherJammer/TINGESO_Evaluacion1_VillalobosMiguel']]])
                sh './gradlew clean build -x test'
            }
        }
        stage('Test'){
            steps{
                sh './gradlew test'
                sh './gradlew sonarqube -Dsonar.projectKey=MueblesStgo-RRHH -Dsonar.host.url=http://localhost:8082 -Dsonar.login=sqa_b6c37755f1602b65efb2a62e051d2acbd1b3b0d6'
            }
        }
        stage('Build Docker Image'){
            steps{
                sh 'docker build -t motherjammer/cs-mueblesstgo:latest .'
            }
        }
        stage('Push Docker Image'){
            steps{
                withCredentials([string(credentialsId: 'docker-hub-password', variable: 'dckpass')]) {
                    sh 'docker login -u motherjammer -p ${dckpass}'
                }
                sh 'docker push motherjammer/cs-mueblesstgo'
            }
        }
    }
    post {
        always {
            sh 'docker logout'
        }
    }
}