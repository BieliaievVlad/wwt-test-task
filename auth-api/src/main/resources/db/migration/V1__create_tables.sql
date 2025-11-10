create schema if not exists task;

create table if not exists task.users (
	id UUID primary key default gen_random_uuid(),
	email VARCHAR(255) unique not null,
	password_hash VARCHAR(255) not null
);

create table if not exists task.logs (
	id UUID primary key default gen_random_uuid(),
	user_id UUID references task.users(id),
	input_text TEXT not null,
	output_text TEXT not null,
	created_at TIMESTAMP not null
);