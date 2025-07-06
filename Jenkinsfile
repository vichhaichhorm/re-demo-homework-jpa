pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/vichhaichhorm/re-demo-homework-jpa.git'
            }
        }

        stage('Build and Test') {
            steps {
                // Make gradlew executable
                sh 'chmod +x gradlew'
                
                // Clean and build the project
                sh './gradlew clean build'
            }
        }

        stage('Archive JAR') {
            steps {
                // Archive the built JAR file
                archiveArtifacts artifacts: 'build/libs/*.jar', allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            // Publish test results if they exist
            publishTestResults testResultsPattern: 'build/test-results/test/*.xml', allowEmptyResults: true
        }
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
