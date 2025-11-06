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
              sh 'ansible-playbook -i hosts playbookCICD.yml '
              }    
             }
           }
                 
   }
      post { 
        always {
         cleanWs()
       }
      succes {
        echo 'success'
      }
     failure {
     echo 'fail'
     }
 }
}
