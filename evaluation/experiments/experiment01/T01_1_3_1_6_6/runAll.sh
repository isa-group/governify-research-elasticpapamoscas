#Tests with: PapamoscasElastic
../runSingleTest.sh T01.01.00-1_6_6 [1,6,6] 60 PapamoscasElastic 760

#Tests with: PapamoscasElasticOnlyOneLevel
../runSingleTest.sh T01.01.00-1_6_6 [1,6,6] 60 PapamoscasElasticOnlyOneLevel 760

#Tests with: PapamoscasElasticOnlyOneLevelPreProvisioned
../runSingleTest.sh T01.01.00-1_6_6 [1,6,6] 60 PapamoscasElasticOnlyOneLevelPreProvisioned 760

node ../summaryAll.js T01
