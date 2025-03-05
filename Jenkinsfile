pipeline {
    agent any
    stages {
        stage ('Build Backend') {
            step {
                bat 'mvn  clean package -DskipTests=true'
            }
        }
    }
}