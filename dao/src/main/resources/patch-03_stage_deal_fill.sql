-- Predefined values, script must be executed before using application
-- Creates 6 predefined rows in table stage_deals

INSERT INTO stage_deals
  SELECT *, '', FALSE
  FROM generate_series((SELECT count(*) FROM stage_deals) + 1, 6);

UPDATE stage_deals
SET name = 'dao.stage_deals.first_contact'
WHERE id = 1 AND NOT name = 'dao.stage_deals.first_contact';

UPDATE stage_deals
SET name = 'dao.stage_deals.negotiations'
WHERE id = 2 AND NOT name = 'dao.stage_deals.negotiations';

UPDATE stage_deals
SET name = 'dao.stage_deals.make_decision'
WHERE id = 3 AND NOT name = 'dao.stage_deals.make_decision';

UPDATE stage_deals
SET name = 'dao.stage_deals.contract_approval'
WHERE id = 4 AND NOT name = 'dao.stage_deals.contract_approval';

UPDATE stage_deals
SET name = 'dao.stage_deals.realized'
WHERE id = 5 AND NOT name = 'dao.stage_deals.realized';

UPDATE stage_deals
SET name = 'dao.stage_deals.failed_closed'
WHERE id = 6 AND NOT name = 'dao.stage_deals.failed_closed';
