#Tests with: PapamoscasElastic
../runSingleTest.sh T07.01.00-5_2_8 [5,2,8] 60 PapamoscasElastic 760
../runSingleTest.sh T07.01.01-5_2_8 [5,2,8] 60 PapamoscasElastic 760
../runSingleTest.sh T07.01.02-5_2_8 [5,2,8] 60 PapamoscasElastic 760

#Tests with: PapamoscasElasticOnlyOneLevel
../runSingleTest.sh T07.01.00-5_2_8 [5,2,8] 60 PapamoscasElasticOnly###OneLevel 760
../runSingleTest.sh T07.01.01-5_2_8 [5,2,8] 60 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T07.01.02-5_2_8 [5,2,8] 60 PapamoscasElasticOnlyOneLevel 760

#Tests with: PapamoscasElasticOnlyOneLevelPreProvisioned
../runSingleTest.sh T07.01.00-5_2_8 [5,2,8] 60 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T07.01.01-5_2_8 [5,2,8] 60 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T07.01.02-5_2_8 [5,2,8] 60 PapamoscasElasticOnlyOneLevelPreProvisioned 760

node ../summaryAll.js T07