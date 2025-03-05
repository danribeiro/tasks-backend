pipeline {
    agent any
    stage {
        stage ('Build Backend') {
            step {
                bat 'mvn  clean package -DskipTests=true'
            }
        }
    }
}