pipeline {
    agent none

    environment {
        GRADLE_USER_HOME = '/cache/gradle'
        IMAGE = 'daves125125/ci-sample-service-java'
    }

    stages {

        stage('Build') {
            agent {
                docker {
                    image 'openjdk:8-jdk'
                    args '-v /cache:/cache'
                }
            }
            steps {
                sh './gradlew compileJava'
                sh './gradlew assemble'
            }
        }

        stage('Test') {
            agent {
                docker {
                    image 'openjdk:8-jdk'
                    args '-v /cache:/cache'
                }
            }
            steps {
                sh './gradlew test'
            }
            post {
                always {
                    junit "build/test-results/**/*.xml"
                }
            }
        }

        stage('Deploy Snapshot') {
            agent any
            steps {
                script {
                    def HASH = sh returnStdout: true, script: 'git rev-parse HEAD'
                    sh """
                        docker build -t ${IMAGE} .
                        docker tag ${IMAGE} ${IMAGE}:${HASH}
                        docker push ${IMAGE}:${HASH}
                    """
                }
                deleteDir()
            }
        }

        stage('Deploy Release') {
            agent any
            when {
                branch 'master'
            }
            steps {
                sh """
                    docker tag ${IMAGE} ${IMAGE}:latest
                    docker push ${IMAGE}:latest
                """
            }
        }
    }

}
