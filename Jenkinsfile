pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/vichhaichhorm/re-demo-homework-jpa.git'
            }
        }

        stage('Build without Tests') {
            steps {
                // Make gradlew executable
                sh 'chmod +x gradlew'
                
                // Clean and build the project without running tests
                sh './gradlew clean build -x test'
            }
        }

        stage('Run Tests (Optional)') {
            steps {
                script {
                    try {
                        // Try to run tests but don't fail the build if they fail
                        sh './gradlew test'
                    } catch (Exception e) {
                        echo "Tests failed, but continuing with build: ${e.getMessage()}"
                        currentBuild.result = 'UNSTABLE'
                    }
                }
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
            // Publish test results using junit (standard Jenkins method)
            script {
                if (fileExists('build/test-results/test/*.xml')) {
                    junit 'build/test-results/test/*.xml'
                }
            }
        }
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed!'
        }
        unstable {
            echo 'Build completed but tests failed!'
        }
    }
}
