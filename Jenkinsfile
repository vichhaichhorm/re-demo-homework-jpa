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
            // Send Telegram notification on success
            script {
                def message = """
✅ *Build Success*
Project: ${env.JOB_NAME}
Build: #${env.BUILD_NUMBER}
Branch: main
Status: SUCCESS
Duration: ${currentBuild.durationString}
Triggered by: ${currentBuild.getBuildCauses('hudson.model.Cause$UserIdCause')?.userId?.getAt(0) ?: 'System'}
Build URL: ${env.BUILD_URL}
"""
                sh """
                curl -s -X POST https://api.telegram.org/bot8131016042:AAHKIXlY3VUMP_GDpZ6euV_-OvoqJJtMkhs/sendMessage \
                -d chat_id=1160863616 \
                -d text='${message}' \
                -d parse_mode='Markdown'
                """
            }
        }
        failure {
            echo 'Build failed!'
            // Send Telegram notification on failure
            script {
                def message = """
❌ *Build Failed*
Project: ${env.JOB_NAME}
Build: #${env.BUILD_NUMBER}
Branch: main
Status: FAILED
Duration: ${currentBuild.durationString}
Triggered by: ${currentBuild.getBuildCauses('hudson.model.Cause$UserIdCause')?.userId?.getAt(0) ?: 'System'}
Build URL: ${env.BUILD_URL}
"""
                sh """
                curl -s -X POST https://api.telegram.org/bot8131016042:AAHKIXlY3VUMP_GDpZ6euV_-OvoqJJtMkhs/sendMessage \
                -d chat_id=1160863616 \
                -d text='${message}' \
                -d parse_mode='Markdown'
                """
            }
        }
    }
}
