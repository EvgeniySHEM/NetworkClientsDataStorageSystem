CREATE TABLE public.users (
                              id serial4 NOT NULL,
                              login varchar(50) NOT NULL,
                              "password" varchar(50) NOT NULL,
                              CONSTRAINT users_login_key UNIQUE (login),
                              CONSTRAINT users_pkey PRIMARY KEY (id)
);

INSERT INTO public.users (login, "password") VALUES('Mark', '123qwe'),
                                                   ('Yana', '123qwe');


CREATE TABLE public.client (
                               clientid serial4 NOT NULL,
                               clientname varchar(100) NOT NULL,
                               "type" varchar(20) NOT NULL,
                               added varchar(25) NOT NULL,
                               CONSTRAINT client_pkey PRIMARY KEY (clientid)
);

CREATE TABLE public.address (
                                id serial4 NOT NULL,
                                ip varchar(25) NOT NULL,
                                mac varchar(20) NOT NULL,
                                model varchar(100) NOT NULL,
                                address varchar(200) NOT NULL,
                                clientid int4 NOT NULL,
                                CONSTRAINT address_pkey PRIMARY KEY (id)
);

ALTER TABLE public.address ADD CONSTRAINT address_clientid_fkey FOREIGN KEY (clientid) REFERENCES public.client(clientid);