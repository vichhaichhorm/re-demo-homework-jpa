pipeline {
    agent any

    environment {
        // Define Java version and Gradle options
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64'  // Adjust path as needed
        GRADLE_OPTS = '-Dorg.gradle.daemon=false'
    }

    tools {
        // Define the JDK version - adjust version as needed
        jdk 'jdk-17'  // Make sure this matches your Jenkins global tool configuration
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/vichhaichhorm/re-demo-homework-jpa.git'
            }
        }

        stage('Clean') {
            steps {
                sh './gradlew clean'
            }
        }

        stage('Compile') {
            steps {
                sh './gradlew compileJava'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test'
            }
            post {
                always {
                    // Publish test results
                    publishTestResults testResultsPattern: 'build/test-results/test/*.xml'
                    // Archive test reports
                    publishHTML([
                        allowMissing: false,
                        alwaysLinkToLastBuild: false,
                        keepAll: true,
                        reportDir: 'build/reports/tests/test',
                        reportFiles: 'index.html',
                        reportName: 'Test Report'
                    ])
                }
            }
        }

        stage('Build') {
            steps {
                sh './gradlew build -x test'  // Skip tests as they were run in previous stage
            }
        }

        stage('Package') {
            steps {
                sh './gradlew bootJar'
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
            }
        }
    }

    post {
        always {
            // Clean workspace after build
            cleanWs()
        }
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed!'
        }
        unstable {
            echo 'Build is unstable!'
        }
    }
}
