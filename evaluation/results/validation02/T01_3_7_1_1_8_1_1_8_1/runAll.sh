#Tests with: PapamoscasElastic
../runSingleTest.sh T01.01.00-1_1_8_1_1_8_1 [1,1,8,1,1,8,1] 120 PapamoscasElastic 760
../runSingleTest.sh T01.01.01-1_1_8_1_1_8_1 [1,1,8,1,1,8,1] 120 PapamoscasElastic 760
../runSingleTest.sh T01.01.02-1_1_8_1_1_8_1 [1,1,8,1,1,8,1] 120 PapamoscasElastic 760

#Tests with: PapamoscasElasticOnlyOneLevel
../runSingleTest.sh T01.01.00-1_1_8_1_1_8_1 [1,1,8,1,1,8,1] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T01.01.01-1_1_8_1_1_8_1 [1,1,8,1,1,8,1] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T01.01.02-1_1_8_1_1_8_1 [1,1,8,1,1,8,1] 120 PapamoscasElasticOnlyOneLevel 760

#Tests with: PapamoscasElasticOnlyOneLevelPreProvisioned
../runSingleTest.sh T01.01.00-1_1_8_1_1_8_1 [1,1,8,1,1,8,1] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T01.01.01-1_1_8_1_1_8_1 [1,1,8,1,1,8,1] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T01.01.02-1_1_8_1_1_8_1 [1,1,8,1,1,8,1] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760

node ../summaryAll.js T01