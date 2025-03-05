pipeline {
    agent any
    stages {
        stage ('Build Backend') {
            steps {
                sh 'mvn clean package -DskipTests=true'
            }
        }
        stage ('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}