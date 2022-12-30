pipeline {
    agent {
        docker {
            image 'maven:3.8.6'
            args '-p 5678:5678 -v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Approval') {
            steps {
                input message: 'Lanjutkan ke tahap Deploy? (Tekan "Proceed" untuk melanjutkan)'
            }
        }
        stage('Deploy') {
            steps {
                sh './jenkins/scripts/deploy.sh'
                sh './jenkins/scripts/kill.sh'
            }
        }
    }
}
