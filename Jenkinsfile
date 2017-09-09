pipeline {
    agent none
    stages {
        stage('Package') {

            agent {
                docker 'openjdk:8-jdk'
                args['-v /cache:/cache']
            }

            steps {
                step {
                    gitVars = checkout scm

                    sh './gradlew compileJava'
                    sh './gradlew assemble'

                    dockerBuild = docker.build "daves125125/ci-sample-service:${gitVars["GIT_COMMIT"]}"
                }
            }
        }
    }
}
