pipeline {
    agent any
    stages {
        stage ('Build Backend') {
            steps {
                sh 'mvn clean package -DskipTests=true'
            }
        }
        stage ('Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }
        stage ('Sonar Analysis') {
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps {
                withSonarQubeEnv('SONAR_LOCAL'){
                    sh "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.projectName='DeployBack' -Dsonar.host.url=http://localhost:9000 -Dsonar.token=sqp_36e99ff48340f23af5d8029e82c5d0014a6e6ad3 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/mvn/**,**/src/test/**,**/model/**,**Application.java"
                }
            }
        }
        stage ('Quality Gate') {
            steps {
                sleep(5)
                timeout(time:1, unit: 'MINUTES'){
                     waitForQualityGate abortPipeline:true 
                }
            }
        }
        stage('Deploy backend'){
            steps {
                deploy adapters: [tomcat8(credentialsId: '8edba374-524d-4dd9-b4e1-d0738709355b', path: '', url: 'http://localhost:8001')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
            }
        }
        stage ('API test') {
            steps {
                dir('api-test'){
                    git 'https://github.com/danribeiro/tasks-api-test'
                    sh 'mvn test'
                }
            }
        }
        stage ('Deploy Frontend') {
            steps {
                dir('frontend'){
                    git 'https://github.com/danribeiro/tasks-frontend'
                    sh 'mvn clean package'
                    deploy adapters: [tomcat8(credentialsId: '8edba374-524d-4dd9-b4e1-d0738709355b', path: '', url: 'http://localhost:8001')], contextPath: 'tasks', war: 'target/tasks.war'
                }
            }
        }
        stage ('Functional test') {
            steps {
                dir('functional-test'){
                    git 'https://github.com/danribeiro/tasks-functional-test'
                    sh 'mvn test'
                }
            }
        }
    }
}