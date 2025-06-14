PGDMP      4                }            mmt     16.2    16.2 .               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16525    mmt     DATABASE     y   CREATE DATABASE "mmt " WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_India.1252';
    DROP DATABASE "mmt ";
                postgres    false            �            1259    16527    User    TABLE     �   CREATE TABLE public."User" (
    u_id integer NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    role character varying(255) NOT NULL
);
    DROP TABLE public."User";
       public         heap    postgres    false            �            1259    16526    User_u_id_seq    SEQUENCE     �   CREATE SEQUENCE public."User_u_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public."User_u_id_seq";
       public          postgres    false    216                       0    0    User_u_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public."User_u_id_seq" OWNED BY public."User".u_id;
          public          postgres    false    215            �            1259    16708    hotel    TABLE       CREATE TABLE public.hotel (
    hotel_id integer NOT NULL,
    hotel_name character varying(255) NOT NULL,
    hotel_address character varying(255) NOT NULL,
    hotel_price numeric(10,2) NOT NULL,
    state character varying(255) NOT NULL,
    vacancy boolean DEFAULT true
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    16707    hotel_hotel_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_hotel_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hotel_hotel_id_seq;
       public          postgres    false    221                       0    0    hotel_hotel_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.hotel_hotel_id_seq OWNED BY public.hotel.hotel_id;
          public          postgres    false    220            �            1259    16718    payment    TABLE       CREATE TABLE public.payment (
    pay_id integer NOT NULL,
    u_id integer NOT NULL,
    t_id integer NOT NULL,
    hotel_id integer,
    amount numeric NOT NULL,
    payment_method character varying(255) NOT NULL,
    pay_td timestamp with time zone NOT NULL
);
    DROP TABLE public.payment;
       public         heap    postgres    false            �            1259    16717    payment_pay_id_seq    SEQUENCE     �   CREATE SEQUENCE public.payment_pay_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.payment_pay_id_seq;
       public          postgres    false    223                       0    0    payment_pay_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.payment_pay_id_seq OWNED BY public.payment.pay_id;
          public          postgres    false    222            �            1259    16630 	   transport    TABLE     L  CREATE TABLE public.transport (
    u_id integer NOT NULL,
    t_id integer NOT NULL,
    transport_mode character varying(255) NOT NULL,
    price numeric NOT NULL,
    source character varying(255) NOT NULL,
    destination character varying(255) NOT NULL,
    tr_no character varying(255) NOT NULL,
    tr_id integer NOT NULL
);
    DROP TABLE public.transport;
       public         heap    postgres    false            �            1259    16772    transport_tr_id_seq    SEQUENCE     �   CREATE SEQUENCE public.transport_tr_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.transport_tr_id_seq;
       public          postgres    false    219                       0    0    transport_tr_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.transport_tr_id_seq OWNED BY public.transport.tr_id;
          public          postgres    false    224            �            1259    16538    trip    TABLE       CREATE TABLE public.trip (
    t_id integer NOT NULL,
    u_id integer NOT NULL,
    source character varying(255) NOT NULL,
    destination character varying(255) NOT NULL,
    str_td timestamp with time zone NOT NULL,
    end_td timestamp with time zone NOT NULL
);
    DROP TABLE public.trip;
       public         heap    postgres    false            �            1259    16537    trip_t_id_seq    SEQUENCE     �   CREATE SEQUENCE public.trip_t_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.trip_t_id_seq;
       public          postgres    false    218                       0    0    trip_t_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.trip_t_id_seq OWNED BY public.trip.t_id;
          public          postgres    false    217            d           2604    16530 	   User u_id    DEFAULT     j   ALTER TABLE ONLY public."User" ALTER COLUMN u_id SET DEFAULT nextval('public."User_u_id_seq"'::regclass);
 :   ALTER TABLE public."User" ALTER COLUMN u_id DROP DEFAULT;
       public          postgres    false    215    216    216            g           2604    16711    hotel hotel_id    DEFAULT     p   ALTER TABLE ONLY public.hotel ALTER COLUMN hotel_id SET DEFAULT nextval('public.hotel_hotel_id_seq'::regclass);
 =   ALTER TABLE public.hotel ALTER COLUMN hotel_id DROP DEFAULT;
       public          postgres    false    221    220    221            i           2604    16721    payment pay_id    DEFAULT     p   ALTER TABLE ONLY public.payment ALTER COLUMN pay_id SET DEFAULT nextval('public.payment_pay_id_seq'::regclass);
 =   ALTER TABLE public.payment ALTER COLUMN pay_id DROP DEFAULT;
       public          postgres    false    222    223    223            f           2604    16773    transport tr_id    DEFAULT     r   ALTER TABLE ONLY public.transport ALTER COLUMN tr_id SET DEFAULT nextval('public.transport_tr_id_seq'::regclass);
 >   ALTER TABLE public.transport ALTER COLUMN tr_id DROP DEFAULT;
       public          postgres    false    224    219            e           2604    16541 	   trip t_id    DEFAULT     f   ALTER TABLE ONLY public.trip ALTER COLUMN t_id SET DEFAULT nextval('public.trip_t_id_seq'::regclass);
 8   ALTER TABLE public.trip ALTER COLUMN t_id DROP DEFAULT;
       public          postgres    false    218    217    218                      0    16527    User 
   TABLE DATA           G   COPY public."User" (u_id, username, password, email, role) FROM stdin;
    public          postgres    false    216   �4                 0    16708    hotel 
   TABLE DATA           a   COPY public.hotel (hotel_id, hotel_name, hotel_address, hotel_price, state, vacancy) FROM stdin;
    public          postgres    false    221   �5                 0    16718    payment 
   TABLE DATA           _   COPY public.payment (pay_id, u_id, t_id, hotel_id, amount, payment_method, pay_td) FROM stdin;
    public          postgres    false    223   7                 0    16630 	   transport 
   TABLE DATA           i   COPY public.transport (u_id, t_id, transport_mode, price, source, destination, tr_no, tr_id) FROM stdin;
    public          postgres    false    219   �7                 0    16538    trip 
   TABLE DATA           O   COPY public.trip (t_id, u_id, source, destination, str_td, end_td) FROM stdin;
    public          postgres    false    218   �8                   0    0    User_u_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public."User_u_id_seq"', 6, true);
          public          postgres    false    215            !           0    0    hotel_hotel_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hotel_hotel_id_seq', 10, true);
          public          postgres    false    220            "           0    0    payment_pay_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.payment_pay_id_seq', 10, true);
          public          postgres    false    222            #           0    0    transport_tr_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.transport_tr_id_seq', 19, true);
          public          postgres    false    224            $           0    0    trip_t_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.trip_t_id_seq', 51, true);
          public          postgres    false    217            k           2606    16536    User User_email_key 
   CONSTRAINT     S   ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "User_email_key" UNIQUE (email);
 A   ALTER TABLE ONLY public."User" DROP CONSTRAINT "User_email_key";
       public            postgres    false    216            m           2606    16534    User User_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "User_pkey" PRIMARY KEY (u_id);
 <   ALTER TABLE ONLY public."User" DROP CONSTRAINT "User_pkey";
       public            postgres    false    216            s           2606    16716    hotel hotel_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (hotel_id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    221            u           2606    16725    payment payment_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.payment
    ADD CONSTRAINT payment_pkey PRIMARY KEY (pay_id);
 >   ALTER TABLE ONLY public.payment DROP CONSTRAINT payment_pkey;
       public            postgres    false    223            q           2606    16780    transport transport_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.transport
    ADD CONSTRAINT transport_pkey PRIMARY KEY (tr_id);
 B   ALTER TABLE ONLY public.transport DROP CONSTRAINT transport_pkey;
       public            postgres    false    219            o           2606    16545    trip trip_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.trip
    ADD CONSTRAINT trip_pkey PRIMARY KEY (t_id);
 8   ALTER TABLE ONLY public.trip DROP CONSTRAINT trip_pkey;
       public            postgres    false    218            y           2606    16736    payment payment_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.payment
    ADD CONSTRAINT payment_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(hotel_id);
 G   ALTER TABLE ONLY public.payment DROP CONSTRAINT payment_hotel_id_fkey;
       public          postgres    false    221    223    4723            z           2606    16731    payment payment_t_id_fkey    FK CONSTRAINT     v   ALTER TABLE ONLY public.payment
    ADD CONSTRAINT payment_t_id_fkey FOREIGN KEY (t_id) REFERENCES public.trip(t_id);
 C   ALTER TABLE ONLY public.payment DROP CONSTRAINT payment_t_id_fkey;
       public          postgres    false    218    4719    223            {           2606    16726    payment payment_u_id_fkey    FK CONSTRAINT     x   ALTER TABLE ONLY public.payment
    ADD CONSTRAINT payment_u_id_fkey FOREIGN KEY (u_id) REFERENCES public."User"(u_id);
 C   ALTER TABLE ONLY public.payment DROP CONSTRAINT payment_u_id_fkey;
       public          postgres    false    4717    216    223            w           2606    16642    transport transport_t_id_fkey    FK CONSTRAINT     z   ALTER TABLE ONLY public.transport
    ADD CONSTRAINT transport_t_id_fkey FOREIGN KEY (t_id) REFERENCES public.trip(t_id);
 G   ALTER TABLE ONLY public.transport DROP CONSTRAINT transport_t_id_fkey;
       public          postgres    false    219    218    4719            x           2606    16637    transport transport_u_id_fkey    FK CONSTRAINT     |   ALTER TABLE ONLY public.transport
    ADD CONSTRAINT transport_u_id_fkey FOREIGN KEY (u_id) REFERENCES public."User"(u_id);
 G   ALTER TABLE ONLY public.transport DROP CONSTRAINT transport_u_id_fkey;
       public          postgres    false    216    4717    219            v           2606    16546    trip trip_u_id_fkey    FK CONSTRAINT     r   ALTER TABLE ONLY public.trip
    ADD CONSTRAINT trip_u_id_fkey FOREIGN KEY (u_id) REFERENCES public."User"(u_id);
 =   ALTER TABLE ONLY public.trip DROP CONSTRAINT trip_u_id_fkey;
       public          postgres    false    216    4717    218               �   x�m�A
� @ѵFHԴ�� �8�:A#��Ķ����39�6��J�T����r���Ol�P�ɬ#��:��x�d������ƒW�� ڻԆ��}(ﻺ`u�ƒ4��� ��ٻ�iGf���_����l�m�`��� e          w  x�e�oO�0�_�>E? �n0`o�O1ߜ�ckKJ��{m戚�Yo{���4�k���2
�l����f��쎤5̥�#)a��j�!�$���Bs$u7��$��s�B+��@��,r	+mUCi[㑌xT�)�0��\d��v�ޒ��C�Y���P'X��e�C-b"He*���%7�{�� �O�5/�j����~�� �p��C&3j1����ۓ��(�/
(���#o0�ѨP<35��ĥ�����w$�Ӎ��>������mZq�pۈG�U����x��;��u���n͓?ш���g��F{�^��+:�ޅ��2+��o<����VEm/�i�&V�=��������␓���$�;MÄ         �   x�}Ͻ
�@�z��%�����]����	$� ��o�	A��mg����$��T�-���y��&Sa:������AQ�!z�XE�"�R�� �	]i��21ؽ��(�KV��U (ڀ�����M$/(��4�*��2_��8m��URhI.�z]�=�5=         .  x�e��N1��٧����ӟ�"�TvM0�$��
>�-�����:93�@y}�6�#�d���������lA��PRz���:������`K�)p�R7M:�6m��RV�*>Cy�)��T�g�a��I�N	�I��z �XC���!�c����ʗi�3�+f��m��a=/��@*[��w#B�2fi�S���:�������z4-�����L��R#ݸke���$^3Pn�����70���C�Ķ��$�:W����Z���ǎ��J��	����(�?J�W         �  x���]k�0���_��������]>�M{�B�`7����8��J�}%o�����|ax���W�@�<d�����dc��yVeጫ	��(�����S�`}��q��j��F�-��!l�s�c�x�g��dU�[��/�Pv���$L�b�"�$�ͩ9M�XU�deq#(0��"K�R~`S�:��t=]M��$e�0NR 7��_�5����{�%��Ԟ>CL�(>3��YU|��1���c��S`��7��6�of���� ��VE�5^p���� 8�,�����<w���q�c9��l=�O�@8����_p�\�(�Ԑd��r�W&��P�X�:�I�����
:E��tGr�Y�Gc�u�AA�s𕈷$���Q����rv\>?���CwXk;��Mو�%m� �KfQ�];�o�����s��D���c]=������|81G�T��3��x�|���{6�ܭ&)v}o�;E^����N5/(Wv�p���G��Q����6     