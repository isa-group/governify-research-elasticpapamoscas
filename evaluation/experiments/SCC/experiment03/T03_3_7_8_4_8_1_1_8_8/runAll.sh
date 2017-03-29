#Tests with: PapamoscasElastic
../runSingleTest.sh T03.01.00-8_4_8_1_1_8_8 [8,4,8,1,1,8,8] 120 PapamoscasElastic 760
../runSingleTest.sh T03.01.01-8_4_8_1_1_8_8 [8,4,8,1,1,8,8] 120 PapamoscasElastic 760
../runSingleTest.sh T03.01.02-8_4_8_1_1_8_8 [8,4,8,1,1,8,8] 120 PapamoscasElastic 760

#Tests with: PapamoscasElasticOnlyOneLevel
../runSingleTest.sh T03.01.00-8_4_8_1_1_8_8 [8,4,8,1,1,8,8] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T03.01.01-8_4_8_1_1_8_8 [8,4,8,1,1,8,8] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T03.01.02-8_4_8_1_1_8_8 [8,4,8,1,1,8,8] 120 PapamoscasElasticOnlyOneLevel 760

#Tests with: PapamoscasElasticOnlyOneLevelPreProvisioned
../runSingleTest.sh T03.01.00-8_4_8_1_1_8_8 [8,4,8,1,1,8,8] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T03.01.01-8_4_8_1_1_8_8 [8,4,8,1,1,8,8] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T03.01.02-8_4_8_1_1_8_8 [8,4,8,1,1,8,8] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760

node ../summaryAll.js T03
