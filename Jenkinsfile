pipeline {
    agent any // This means any available Jenkins agent can run this pipeline

    environment {
        // Optional: Define environment variables if needed, e.g., for deployment credentials
        // DEPLOY_USER = credentials('your-ssh-credential-id')
        // DEPLOY_SERVER = 'your-server-ip'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/vichhaichhorm/re-demo-homework-jpa.git'
            }
        }

        stage('Build') {
            steps {
                // Use the Gradle Wrapper to build the project
                sh './gradlew clean build'
            }
        }

        stage('Test') {
            steps {
                // Run tests. The 'build' task typically runs tests already,
                // but you can add a separate test stage if you have specific test tasks.
                sh './gradlew test'
                // Publish JUnit test results for Jenkins to display
                junit '**/build/test-results/test/*.xml'
            }
        }

        stage('Package') {
            steps {
                // Create the executable JAR file
                sh './gradlew bootJar'
            }
        }

        stage('Deploy') {
            steps {
                // *** IMPORTANT: This is a placeholder for your actual deployment logic. ***
                // You will need to customize this significantly based on how you want to deploy.

                // Example 1: Copy JAR to a remote server using SCP
                // This requires an SSH agent on your Jenkins server/agent and configured SSH credentials in Jenkins.
                // withCredentials([sshUserPrivateKey(credentialsId: 'your-ssh-credential-id', keyFileVariable: 'KEY')]) {
                //     sh "scp -i ${KEY} build/libs/*.jar user@your-server-ip:/path/to/deploy/"
                //     sh "ssh -i ${KEY} user@your-server-ip 'nohup java -jar /path/to/deploy/your-app.jar > /dev/null 2>&1 &'"
                // }

                // Example 2: Build and push a Docker image (requires Docker installed on agent)
                // sh "docker build -t your-docker-repo/your-app:latest ."
                // withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                //    sh "docker login -u ${DOCKER_USERNAME} -p ${DOCKER_PASSWORD}"
                //    sh "docker push your-docker-repo/your-app:latest"
                // }

                // Example 3: Simple local run (for testing purposes, not for production deployment)
                // This will start the app and keep the Jenkins job running until the app stops.
                // For actual deployment, you usually want to run it as a background service.
                // sh 'java -jar build/libs/*.jar' // This would block the job.

                echo "Deployment logic goes here!"
                echo "The executable JAR is located at: build/libs/re-demo-homework-jpa-0.0.1-SNAPSHOT.jar"
                // For a simple test, you can just indicate the JAR is built.
                // You would manually copy and run it for initial testing if no automated deployment is set up yet.
            }
        }
    }

    post {
        always {
            // Clean up workspace after build
            cleanWs()
        }
        success {
            echo 'Pipeline finished successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}