node {

    def dockerBuild
    def gitVars
    env.GRADLE_USER_HOME = "/cache"

    stage('Package') {

        gitVars = checkout scm

        docker.image('openjdk:8-jdk').inside("-v /cache:/cache") {
            sh './gradlew compileJava'
            sh './gradlew assemble'
        }

        dockerBuild = docker.build "daves125125/ci-sample-service:${gitVars["GIT_COMMIT"]}"
    }

    stage('Test') {
        docker.image('openjdk:8-jdk').inside("-v /cache:/cache") {
            sh './gradlew test'
        }
    }

    stage('Deploy') {
        when {
            expression {
                return $ { gitVars["GIT_BRANCH"] } == 'origin/master'
            }
        }

        docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-login') {
            dockerBuild.push()
            dockerBuild.push 'latest'
        }
    }

}
