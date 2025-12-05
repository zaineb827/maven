pipeline{
        agent any 

	tools{
	  maven 'M2_HOME'
           }
       stages{ 
         stage ('Checkout code')
	   {
             steps{
               git branch: 'main' , url: 'https://github.com/zaineb827/maven.git'
                 }
          }
          stage ('Build maven')
	   {
		steps{
		 sh 'mvn clean install'
		}
	     }

          stage ('Deploy using Ansible playbook') {
          steps {
            script {
              sh '/var/lib/jenkins/.local/bin/ansible-playbook -i hosts playbookCICD.yml '
              }    
             }
           }

          stage('Verify Prometheus scraping') {
            steps {
                script {
                    // Vérifie que le Pod est prêt
                    sh 'kubectl get pods -l app=country-service'

                    // Vérifie que l'endpoint /actuator/prometheus répond
                    sh 'curl -s http://localhost:30008/actuator/prometheus | head -n 10'

                    echo 'My app is exposed on NodePort 30008. Prometheus can now scrape metrics!'
                }
            }
        }
                 
   }
      post { 
        always {
         cleanWs()
       }
      success {
        echo 'success'
      }
     failure {
     echo 'fail'
     }
 }
}
