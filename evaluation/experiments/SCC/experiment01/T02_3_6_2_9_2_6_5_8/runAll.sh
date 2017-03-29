#Tests with: PapamoscasElastic
../runSingleTest.sh T02.01.00-2_9_2_6_5_8 [2,9,2,6,5,8] 120 PapamoscasElastic 760
../runSingleTest.sh T02.01.01-2_9_2_6_5_8 [2,9,2,6,5,8] 120 PapamoscasElastic 760
../runSingleTest.sh T02.01.02-2_9_2_6_5_8 [2,9,2,6,5,8] 120 PapamoscasElastic 760

#Tests with: PapamoscasElasticOnlyOneLevel
../runSingleTest.sh T02.01.00-2_9_2_6_5_8 [2,9,2,6,5,8] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T02.01.01-2_9_2_6_5_8 [2,9,2,6,5,8] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T02.01.02-2_9_2_6_5_8 [2,9,2,6,5,8] 120 PapamoscasElasticOnlyOneLevel 760

#Tests with: PapamoscasElasticOnlyOneLevelPreProvisioned
../runSingleTest.sh T02.01.00-2_9_2_6_5_8 [2,9,2,6,5,8] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T02.01.01-2_9_2_6_5_8 [2,9,2,6,5,8] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T02.01.02-2_9_2_6_5_8 [2,9,2,6,5,8] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760

node ../summaryAll.js T02
