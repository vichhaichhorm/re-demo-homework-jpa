pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/vichhaichhorm/re-demo-homework-jpa.git'
            }
        }

        stage('Build') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean build -x test'
            }
        }

        stage('Archive JAR') {
            steps {
                archiveArtifacts artifacts: 'build/libs/*.jar', allowEmptyArchive: true
            }
        }
    }

    post {
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
