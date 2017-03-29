#Tests with: PapamoscasElastic
../runSingleTest.sh T06.01.00-6_4_3_8_7_4_8 [6,4,3,8,7,4,8] 120 PapamoscasElastic 760
../runSingleTest.sh T06.01.01-6_4_3_8_7_4_8 [6,4,3,8,7,4,8] 120 PapamoscasElastic 760
../runSingleTest.sh T06.01.02-6_4_3_8_7_4_8 [6,4,3,8,7,4,8] 120 PapamoscasElastic 760

#Tests with: PapamoscasElasticOnlyOneLevel
../runSingleTest.sh T06.01.00-6_4_3_8_7_4_8 [6,4,3,8,7,4,8] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T06.01.01-6_4_3_8_7_4_8 [6,4,3,8,7,4,8] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T06.01.02-6_4_3_8_7_4_8 [6,4,3,8,7,4,8] 120 PapamoscasElasticOnlyOneLevel 760

#Tests with: PapamoscasElasticOnlyOneLevelPreProvisioned
../runSingleTest.sh T06.01.00-6_4_3_8_7_4_8 [6,4,3,8,7,4,8] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T06.01.01-6_4_3_8_7_4_8 [6,4,3,8,7,4,8] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T06.01.02-6_4_3_8_7_4_8 [6,4,3,8,7,4,8] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760

node ../summaryAll.js T06