#Tests with: PapamoscasElastic
../runSingleTest.sh T03.01.00-1_6_3_8_8_1_8 [1,6,3,8,8,1,8] 90 PapamoscasElastic 760
../runSingleTest.sh T03.01.01-1_6_3_8_8_1_8 [1,6,3,8,8,1,8] 90 PapamoscasElastic 760
../runSingleTest.sh T03.01.02-1_6_3_8_8_1_8 [1,6,3,8,8,1,8] 90 PapamoscasElastic 760

#Tests with: PapamoscasElasticOnlyOneLevel
../runSingleTest.sh T03.01.00-1_6_3_8_8_1_8 [1,6,3,8,8,1,8] 90 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T03.01.01-1_6_3_8_8_1_8 [1,6,3,8,8,1,8] 90 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T03.01.02-1_6_3_8_8_1_8 [1,6,3,8,8,1,8] 90 PapamoscasElasticOnlyOneLevel 760

#Tests with: PapamoscasElasticOnlyOneLevelPreProvisioned
../runSingleTest.sh T03.01.00-1_6_3_8_8_1_8 [1,6,3,8,8,1,8] 90 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T03.01.01-1_6_3_8_8_1_8 [1,6,3,8,8,1,8] 90 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T03.01.02-1_6_3_8_8_1_8 [1,6,3,8,8,1,8] 90 PapamoscasElasticOnlyOneLevelPreProvisioned 760

node ../summaryAll.js T03
