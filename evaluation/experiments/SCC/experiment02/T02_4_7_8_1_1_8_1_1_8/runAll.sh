#Tests with: PapamoscasElastic
../runSingleTest.sh T02.01.00-8_1_1_8_1_1_8 [8,1,1,8,1,1,8] 120 PapamoscasElastic 760
../runSingleTest.sh T02.01.01-8_1_1_8_1_1_8 [8,1,1,8,1,1,8] 120 PapamoscasElastic 760
../runSingleTest.sh T02.01.02-8_1_1_8_1_1_8 [8,1,1,8,1,1,8] 120 PapamoscasElastic 760
../runSingleTest.sh T02.01.03-8_1_1_8_1_1_8 [8,1,1,8,1,1,8] 120 PapamoscasElastic 760

#Tests with: PapamoscasElasticOnlyOneLevel
../runSingleTest.sh T02.01.00-8_1_1_8_1_1_8 [8,1,1,8,1,1,8] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T02.01.01-8_1_1_8_1_1_8 [8,1,1,8,1,1,8] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T02.01.02-8_1_1_8_1_1_8 [8,1,1,8,1,1,8] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T02.01.03-8_1_1_8_1_1_8 [8,1,1,8,1,1,8] 120 PapamoscasElasticOnlyOneLevel 760

#Tests with: PapamoscasElasticOnlyOneLevelPreProvisioned
../runSingleTest.sh T02.01.00-8_1_1_8_1_1_8 [8,1,1,8,1,1,8] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T02.01.01-8_1_1_8_1_1_8 [8,1,1,8,1,1,8] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T02.01.02-8_1_1_8_1_1_8 [8,1,1,8,1,1,8] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T02.01.03-8_1_1_8_1_1_8 [8,1,1,8,1,1,8] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760

node ../summaryAll.js T02
